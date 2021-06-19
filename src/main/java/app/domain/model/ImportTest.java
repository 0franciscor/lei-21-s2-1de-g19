package app.domain.model;

import java.io.*;

public class ImportTest {

    private File ficheiroInput;

    private BufferedReader bufferedReader;

    private int[] importRules;

    public ImportTest(){}

    public boolean getFile(String pathName) throws IOException {
        this.ficheiroInput = new File(pathName);
        if(ficheiroInput.exists()) {
            this.bufferedReader = new BufferedReader(new FileReader(ficheiroInput));
            importRules = getRules();
            return true;
        } else
            return false;
    }

    public int[] getRules () throws IOException {
        String linha = bufferedReader.readLine();
        String [] linhaSplitRegra= linha.split(";");

        int [] contaCategorias = null;

        for (String string : linhaSplitRegra) {
            if (string.equalsIgnoreCase("TestType")) {
                contaCategorias = contaCategorias(linhaSplitRegra);
            }
        }
        contaParametrosCategorias(linhaSplitRegra, contaCategorias);

        return contaCategorias;
    }

    public String readListFromCSV() throws IOException {
        String linhaFicheiro = bufferedReader.readLine();
        String [] linhaSplit = linhaFicheiro.split(";");
        return "oi";
    }

    public boolean checkIfExists(String linhaFicheiro){




        return true;

    }







    // MÉTODOS ALGORITMIA UTEIS

    public static int[] contaCategorias (String[] linhaRegra){ //FUNCIONA
        int numCategorias = 0, i = 12;
        do{
            if(linhaRegra[i].equalsIgnoreCase("Category")){
                numCategorias++;
            }
            i++;
        }while(!linhaRegra[i].equalsIgnoreCase("Test_Reg_DateHour"));

        return new int[numCategorias];
    }

    public static void contaParametrosCategorias (String[] linhaRegra, int[] contaCategorias){
        int indiceParametro = 0, i = 13, numParametros = 0;

        do{
            if (!linhaRegra[i].equalsIgnoreCase("Category") && !linhaRegra[i].equalsIgnoreCase("Test_Reg_DateHour")) {
                numParametros++;
            }
            if (linhaRegra[i].equalsIgnoreCase("Category") || linhaRegra[i].equalsIgnoreCase("Test_Reg_DateHour")) {
                contaCategorias[indiceParametro] = numParametros;
                indiceParametro++;
                numParametros = 0;
            }
            i++;
        } while(indiceParametro < contaCategorias.length);
    }
}
