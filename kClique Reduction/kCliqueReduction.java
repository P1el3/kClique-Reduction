import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class kCliqueReduction {
    static int N = 0;
    static int k = 0;
    static int M = 0;
    static int[][] matrix = new int[1000][1000];
    static boolean OK = false;

    public static String SATReduction(){
        int i,j,m,n;
        String sat = "";
        //in acest for creez clauzele de tipul 1
        for(i = 1; i <= k; i++){
            sat += "(";
            for(j = 1; j <= N; j++){
                sat += "a" + j + i; // nodul j este al i-lea din clica
                if(j < N){
                    sat += "V";
                }
            }
            sat += ")^";
        }
        //in acest for creez clauzele de tipul 2
        for(m = 1; m <= N; m++){
            for(i = 1; i <= k; i++){
                for(j = i + 1; j <= k; j++){
                    sat += "(~a" + m + i + "V~a" + m + j + ")^";
                }
            }
        }
        //in acest for creez clauzele de tipul 3
        for(i = 1; i <= k; i++){ 
            for(j = i + 1; j <= k; j++) {
                for (m = 1; m <= N; m++) {
                    for (n = 1; n <= N; n++) {
                        //verific daca nu e muchie intre m si n, unde m si n nu sunt acelasi nod
                        if (matrix[m][n] == 0 && m != n) {
                            sat += "(~a" + m + i + "V~a" + n + j + ")^";
                        }
                    }
                }
            }
        }

        return sat.substring(0,sat.length() - 1);

    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                matrix[i][j] = 0;//populez matricea cu 0 -uri
            }
        File read = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(read));
        //aici sunt citirile
        k = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        int node1, node2;
        try {
            String line;//citesc muchiile si le pun in matricea de adiacenta
            while ((line = br.readLine()) != null) {
                String[] noduri = line.split(" ");
                node1 = Integer.parseInt(noduri[0]);
                node2 = Integer.parseInt(noduri[1]);
                matrix[node1][node2] = 1;
                matrix[node2][node1] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(SATReduction());

    }
}


