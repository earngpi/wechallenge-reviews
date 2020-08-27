# wechallenge-reviews

This repository tracks the source code to achieve *Wongnai WeChallenge - Review Application.* 

## The solution
- Dataset (food_dictionary.txt and test_file.csv) are loaded to Elasticsearch
- Web application implemented via Java Spring Boot exposes three APIs (see below) to client as an intermediate mean to access the database
- Simple website developed in HTML, CSS, Javascript and hosted by Nginx offers a UI for end user wishing to use the three APIs (see below)

## The APIs
1. Get - This interface allows client to view a review by specifying its review ID
2. Search - This interface allows client to view multiple reviews by specifying part of review content
3. Update - This interface allows client to make changes to a review by specifying its review ID

For technical detail, refer to ReviewAPISpecification.yml. Note that this file cannot be rendered by Swagger as two of the APIs violate Swagger syntax by having the same path.