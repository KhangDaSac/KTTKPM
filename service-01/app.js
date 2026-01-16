const express = require('express');
const auth = require('./auth');
const rabbit = require('./rabbit');

const app = express();
app.use(express.json());

app.post('/events', auth, async (req, res) => {
    await rabbit.publish(req.body);
    res.send('Event sent to RabbitMQ');
});

rabbit.connect().then(() => {
    app.listen(8081, () =>
        console.log('Service 1 started on port 8081')
    );
});
