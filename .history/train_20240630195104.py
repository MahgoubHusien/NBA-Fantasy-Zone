# train.py

import argparse
import os
import pandas as pd
import tensorflow as tf
from sklearn.preprocessing import StandardScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout

def model_fn(input_dim):
    model = Sequential()
    model.add(Dense(64, input_dim=input_dim, activation='relu'))
    model.add(Dropout(0.2))
    model.add(Dense(32, activation='relu'))
    model.add(Dense(1))
    model.compile(loss='mean_squared_error', optimizer='adam')
    return model

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    
    # Hyperparameters
    parser.add_argument('--epochs', type=int, default=50)
    parser.add_argument('--batch_size', type=int, default=10)
    
    # Data directories
    parser.add_argument('--train', type=str, default=os.environ['SM_CHANNEL_TRAIN'])
    parser.add_argument('--test', type=str, default=os.environ['SM_CHANNEL_TEST'])
    
    # Model directory
    parser.add_argument('--model_dir', type=str, default=os.environ['SM_MODEL_DIR'])
    
    args = parser.parse_args()
    
    # Load data
    train_data = pd.read_csv(os.path.join(args.train, 'train.csv'))
    test_data = pd.read_csv(os.path.join(args.test, 'test.csv'))
    
    # Ensure the predictors match the 34 input features
    predictors = ['Age', 'G', 'GS', 'MP', 'FG', 'FGA', 'FG%', '3P', '3PA', '3P%', 
                  '2P', '2PA', '2P%', 'eFG%', 'FT', 'FTA', 'FT%', 'ORB', 'DRB', 
                  'TRB', 'AST', 'STL', 'BLK', 'TOV', 'PF', 'PTS', 'Year', 'W', 
                  'L', 'W/L%', 'GB', 'PS/G', 'PA/G', 'SRS']  # Make sure these match your dataset
    target = 'Share'
    
    X_train = train_data[predictors].values
    y_train = train_data[target].values
    X_test = test_data[predictors].values
    y_test = test_data[target].values
    
    scaler = StandardScaler()
    X_train = scaler.fit_transform(X_train)
    X_test = scaler.transform(X_test)
    
    model = model_fn(input_dim=34)  # Specify input_dim as 34
    model.fit(X_train, y_train, epochs=args.epochs, batch_size=args.batch_size, validation_data=(X_test, y_test))
    
    # Save the model to the specified S3 path
    model.save(os.path.join(args.model_dir, '000000001'))
