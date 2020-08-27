import csv
from elasticsearch import Elasticsearch 
from elasticsearch import helpers

import sys
reload(sys)
sys.setdefaultencoding('UTF8')

es_client = Elasticsearch(['http://elasticsearch:9200/'], verify_certs=True)

if not es_client.ping(): exit(1)
else: print(es_client.ping)

with open('test_file.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=';')
    is_header = True
    i = 1
    list_of_docs = []

    for row in csv_reader:
        if is_header:
            is_header = False
            continue

        list_of_docs.append({
                "_index": "reviews",
                "_id": row[0],
                "reviewId": row[0],
                "review": row[1]})
        
        if i == 1000:
            helpers.bulk(es_client, list_of_docs)
            i = 1
            listOfDoc = []
        else:
            i += 1
    
    helpers.bulk(es_client, list_of_docs)
    

with open('food_dictionary.txt') as txt_file:
    line_no = 1
    list_of_docs = []

    for line in txt_file:
        if line_no > 20000:
            break

        line = line.split("\n")[0]

        list_of_docs.append({
                "_index": "food_dictionary",
                "_id": line_no,
                "foodId": line_no,
                "foodName": line})

        line_no += 1
        if i == 1000:
            helpers.bulk(es_client, list_of_docs)
            i = 1
            list_of_docs = []
        else:
            i += 1
    helpers.bulk(es_client, list_of_docs)

csv_file.close()
txt_file.close()
            