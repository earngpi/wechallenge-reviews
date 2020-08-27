FROM python:2
COPY . .
RUN ["chmod", "+x", "/wait-for-it.sh"]
RUN ["chmod", "+x", "./loadInitData.sh"]
CMD ["./wait-for-it.sh" , "elasticsearch:9200" , "--strict", "--" , "./loadInitData.sh"]

