var net = require('net');
var readline = require('readline');
var br = '\r\n';

function evaluate(req) {
  var num = +req;
  if (Number.isNaN(num)) {
    return 'string';
  } else {
    if (req.indexOf('.') < 0) {
      return 'int';
    }
    return 'double';
  }
}

net.createServer(function (socket) {
  socket.setEncoding('ascii');
  var rl = readline.createInterface(socket, socket);
  rl.on('line', function (req) {
    console.log(req);
    if (req === 'exit') return socket.destroy();
    var res;
    socket.write((res = evaluate(req)) + br);
    console.log(res);
  });
}).listen(8003);
console.log('Server listening at 8003');
