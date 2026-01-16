const amqp = require('amqplib');

let channel;

async function connect() {
    const connection = await amqp.connect('amqp://localhost');
    channel = await connection.createChannel();
    await channel.assertExchange('event.exchange', 'topic', { durable: true });
}

async function publish(event) {
    channel.publish(
        'event.exchange',
        'event.created',
        Buffer.from(JSON.stringify(event))
    );
}

module.exports = { connect, publish };
