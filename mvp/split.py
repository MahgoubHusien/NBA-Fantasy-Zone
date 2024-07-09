import pandas as pd

# Load the dataset
df = pd.read_csv('mvp_stats.csv')

# Split the data into train and test sets
train = df[df['Year'] < 2024]
test = df[df['Year'] == 2024]

# Save the train and test datasets to CSV files
train.to_csv('train.csv', index=False)
test.to_csv('test.csv', index=False)

print("Train and test sets have been created and saved as 'train.csv' and 'test.csv'")
