import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class kCliqueBKT {

    static int N = 0;
    static int k = 0;
    static int M = 0;
    static int[] v = new int[1000];
    static int[][] matrix = new int[1000][1000];
    static boolean OK = false;
    static void backTracking(int node, int step){
        if(node == k){ //daca am ajuns la sfarsit
            boolean ok = true;
            for(int i = 1; i <= N; i++){
                if(v[i] == 1){
                    for(int j = 1; j <= N; j++){
                        if(v[j] == 1){
                            if(matrix[i][j] == 0 && i != j){
                                ok = false;
                            }
                        }
                    }
                }
            }
            if(ok){
                OK = true;
            }
            //caut daca nu exista un nod cu legaturi,iar in caz ca nu l gasesc
            //inseamna ca toate nodurile sunt conectate
            return;
        }
        if(step > N){ //daca am iesit din vectorul
            return;
        }

        v[step] = 1;
        backTracking(node+1, step+1); //trec la urmatorul pas

        v[step] = 0;
        backTracking(node, step+1); //trec la urmatorul pas fara sa pun 1

    }

    public static void main(String[] args) throws IOException {
	    for(int i = 1; i <= N; i++){
            v[i] = 0;
        }
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++){
                matrix[i][j] = 0;
        }
        File read = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(read));
        //aici sunt citirile
        k = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        int node1,node2;
       try{
           String line;//citesc muchiile si le pun in matricea de adiacenta
           while ((line = br.readLine()) != null){
               String[] noduri = line.split(" ");
               node1 = Integer.parseInt(noduri[0]);
               node2 = Integer.parseInt(noduri[1]);
               matrix[node1][node2] = 1;
               matrix[node2][node1] = 1;
           }
       }catch (IOException e) {
           e.printStackTrace();}

        backTracking(0, 1);
        if(OK){
            System.out.println("True");
        }
        else System.out.println("False");
    }
}
