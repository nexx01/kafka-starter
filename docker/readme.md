Для запуска кафки, перейдите в директорию docker </p>
<code>
docker-compose up -d
</code>

Чтобы отправить сообщение в kafka используйте команду

<code>
docker exec --interactive --tty broker \
   kafka-console-producer --bootstrap-server broker:9092 \
   --topic test
</code>

Считать все данные из топика можно командой:

<code>
docker exec --interactive --tty broker \
    kafka-console-consumer --bootstrap-server broker:9092 \
    --topic test \
    --from-beginning
</code>

Топик будет создан автоматически.
Но для тренировки следует создать топик вручную:

<code>
docker exec broker \
    kafka-topics --bootstrap-server broker:9092 \
        --create \
        --replication-factor 1 \
        --partitions 3 \
        --topic kafkaCourse-UserAction-ClickStream
</code>

Остановить кафку и удалить все данные:

<code>
docker-compose down
docker rm -f zookeeper broker
</code>