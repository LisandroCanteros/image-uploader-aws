# Image Uploader – Spring Boot + AWS (S3, SQS, RDS)

A backend service built with **Spring Boot** and deployed on **AWS EC2**.  
The application receives image uploads, stores them in **S3**, publishes events to **SQS**, and saves metadata in an **RDS PostgreSQL** database. Uses **IAM instance roles**

## Features

- **Upload images** via REST (`POST /upload`)
- Store image files in **Amazon S3**
- Publish processing events to **Amazon SQS**
- Save metadata (filename, URL, timestamps) in **Amazon RDS PostgreSQL**
- Built using **Spring Boot 3** and **AWS SDK v2**
- Deployed on **AWS EC2**

## Technologies

- **Java 25**
- **Spring Boot 3**
- **AWS S3** for file storage  
- **AWS SQS** for event messaging  
- **AWS RDS PostgreSQL** for persistence  
- **AWS EC2** for hosting the application  
- **IAM Role** for secure auth  
- **Maven** for build

## Architecture

<img width="1099" height="685" alt="image" src="https://github.com/user-attachments/assets/c4b5021b-a691-4cf1-8a93-c466e7a3b26a" />

