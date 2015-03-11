var net = require('net');
var readline = require('readline');
var br = '\r\n';

function evaluate(req) {
  String.prototype.reverse = function () {
    return this.split('').reverse().join('');
  };
  if (req === req.reverse()) {
    return 'pareho';
  } else {
    return 'haan nga agpada';
  }
}

net.createServer(function (socket) {
  socket.setEncoding('ascii');
  var rl = readline.createInterface(socket, socket);
  rl.on('line', function (req) {
    console.log(req);
    if (req === 'bye') return socket.destroy();
    var res;
    socket.write((res = evaluate(req)) + br);
    console.log(res);
  });
}).listen(8003);
console.log('Server listening at 8003');
