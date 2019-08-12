# nodes
Simple library for socket connection (TCP/UDP)

### creating a new node
```java
Node node = Node.build(3000, Protocol.TCP);  //Host Port 3000 Protocol.TCP or Protocol.UDP
```

### sending a message 
```java
node.send(InetAddress.getByName("localhost"), 3001, "Hello!");
```


### receiving messages
```java
node.startReceiver(new ReciverMessageListener() {
  public void receiverMessage(String message, InetAddress address, int port) throws IOException {
    System.out.println("------> "+message);
  }
});
```
