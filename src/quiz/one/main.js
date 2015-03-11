var net = require('net');
var readline = require('readline');
var br = '\r\n';

function evaluate(req) {
  var tokens = req.split(/\+/);
  if (tokens.length != 2) {
    return 'invalid expression';
  }
  var res = (+tokens[0]) + (+tokens[1]);
  if (Number.isNaN(+res)) {
    return 'invalid expression';
  }
  return res;
}

net.createServer(function (socket) {
  socket.setEncoding('ascii');
  var rl = readline.createInterface(socket, socket);
  socket.write('addition expression evaluator' + br);
  rl.on('line', function (req) {
    console.log(req);
    if (req === 'exit') return socket.destroy();
    var res;
    socket.write((res = evaluate(req)) + br);
    console.log(res);
  });
}).listen(8003);
console.log('Server listening at 8003');
