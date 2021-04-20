docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker run --name quorkusbahl -v /mnt/c/code-with-quarkus/:/mnt -itd -p 8080:8080 quorkusbahl bash
sleep 5
docker exec quorkusbahl sh run.sh

