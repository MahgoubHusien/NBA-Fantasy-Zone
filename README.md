# 🏀 NBA Fantasy Zone

Welcome to **NBA Fantasy Zone** — your ultimate hub for real-time NBA stats, game predictions, and MVP forecasts. Built with cutting-edge technologies, NBA Fantasy Zone delivers a seamless and interactive experience for basketball enthusiasts, data analysts, and fantasy sports players.

## 🚀 Features

- **Real-Time NBA Stats**: Stay updated with the latest player and team statistics throughout the NBA season.
- **MVP Prediction**: Our machine learning model predicts the MVP with a **13% improved accuracy** over traditional methods, analyzing data from **14,200 players** across **30 years**.
- **Game Prediction**: Get game outcome predictions with a dataset based on over **23,000 games**.
- **Scalable and Secure**: Deployed on AWS with full containerization for reliability and security.

## 🛠️ Technologies Used

- **Frontend**: [Next.js](https://nextjs.org/), [React.js](https://reactjs.org/)
- **Backend**: [Spring Boot](https://spring.io/projects/spring-boot), [PostgreSQL](https://www.postgresql.org/)
- **Machine Learning**: [RidgeClassifier](https://scikit-learn.org/stable/modules/generated/sklearn.linear_model.RidgeClassifier.html), [XGBoost](https://xgboost.readthedocs.io/), [HistGradientBoostingClassifier](https://scikit-learn.org/stable/modules/generated/sklearn.ensemble.HistGradientBoostingClassifier.html)
- **Cloud Infrastructure**: [AWS Amplify](https://aws.amazon.com/amplify/), [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/), [EC2](https://aws.amazon.com/ec2/), [API Gateway](https://aws.amazon.com/api-gateway/), [AWS RDS](https://aws.amazon.com/rds/)
- **Containerization**: [Docker](https://www.docker.com/)

## 🧠 Machine Learning Models

- **MVP Prediction**: Analyzes player performance metrics over decades to predict the most likely MVP.
- **Game Outcome Prediction**: Evaluates team stats, player metrics, and historical game data to forecast the outcome of upcoming games.

## 🏗️ Architecture

NBA Fantasy Zone is built with a robust, scalable architecture:

- **Frontend**: Deployed on AWS Amplify, providing a responsive and dynamic user interface.
- **Backend**: Powered by Spring Boot, deployed on AWS Elastic Beanstalk for seamless integration and scalability.
- **Database**: PostgreSQL database hosted on AWS RDS, ensuring reliable data storage and retrieval.
- **API Integration**: All services communicate via secure APIs managed by AWS API Gateway.
