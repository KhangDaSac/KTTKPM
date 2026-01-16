const express = require('express');
const auth = require('./auth');
const startConsumer = require('./consumer');

const app = express();

app.get('/health', auth, (req, res) => {
    res.send('Service 2 is running');
});

startConsumer();

app.listen(8082, () =>
    console.log('Service 2 started on port 8082')
);
