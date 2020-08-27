#!/bin/bash

pip install -r requirements.txt --no-cache-dir

echo Loading initial data to Elasticsearch..
python loadInitData.py