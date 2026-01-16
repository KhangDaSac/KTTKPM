const amqp = require('amqplib');

async function startConsumer() {
    const connection = await amqp.connect('amqp://localhost');
    const channel = await connection.createChannel();

    await channel.assertExchange('event.exchange', 'topic', { durable: true });
    await channel.assertQueue('service2.queue', { durable: true });
    await channel.bindQueue('service2.queue', 'event.exchange', 'event.created');

    console.log('Service 2 waiting for events...');

    channel.consume('service2.queue', msg => {
        const event = JSON.parse(msg.content.toString());
        console.log('EVENT RECEIVED:', event);
        channel.ack(msg);
    });
}

module.exports = startConsumer;
