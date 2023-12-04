class Voo{
    int numero;
    String destino; 
    String portao;
    String partida;
    String status;
   
    Voo next;
    
    //construtor
    public Voo(int numero, String destino, String portao, String partida, String status){
        this.numero = numero;
        this.destino = destino; 
        this.portao = portao;
        this.partida = partida;
        this.status = status;
        this.next = null;
    }
}
