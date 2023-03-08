App simples utilizando java e webflux para simular uma aplicação que transmite via websocket um placar hipotético de um jogo de futebol.

O fluxo consiste em 3 etapas:

* receber os resultados atraves da controller Jogos
* emitir essa atualização no Sink da entidade Jogo
* Acompanhar as notificações do Sink no Websocket

Para testar o websocket, foi utilizado o [wscat](https://github.com/jnordberg/wscat)


Exemplos de utilização: 

1 - Processando todos os jogos
Websocket: /stream
Criacão do Jogo na controller:

<img src="https://user-images.githubusercontent.com/40566391/223873705-514142d7-c601-4b67-8013-d745c01257bd.png" width="500" height="500">

Conexão no WebSocket

<img src="https://user-images.githubusercontent.com/40566391/223874635-d3486f1c-c46e-4a7b-8753-3fddf524b42e.png">

Simulação de uma edição do jogo

<img src="https://user-images.githubusercontent.com/40566391/223874999-74441390-aeca-45ff-9d17-a8dd42896346.png" width="500" height="500">

Transmissão do valor alterado no websocket

![image](https://user-images.githubusercontent.com/40566391/223875312-a441d508-6d0c-48e7-ae2b-d45a24b88f10.png)

Criação e transmissão de um novo jogo

<img src="https://user-images.githubusercontent.com/40566391/223875555-3f356b2e-1fa2-450b-af31-abfdf1f2b391.png" width="500" height="500">

![image](https://user-images.githubusercontent.com/40566391/223875645-c11e5559-c3ca-4e90-93ce-e4ab0d2c0d89.png)
