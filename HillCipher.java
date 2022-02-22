import java.io.*;
import java.util.*;
import java.io.*; 
public class HillCipher {
	static final int N = 3;
	static double[][] decrypt = new double[3][1];
	static double[][] a = new double[3][3]; 
	static double[][] b = new double[3][3]; 
	static double[][] mes = new double[3][1]; 
	static double[][] res = new double[3][1];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		getkeymes();
		if(inverse()==false){
			System.out.println("Matrix does not have inverse.\nKey is invalid.");
			return;
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<1;j++){
				for(int k=0;k<3;k++) {
					res[i][j]=res[i][j]+a[i][k]*mes[k][j]; 
				}
			}
		}
			
		System.out.print("\nEncrypted string is :"); 
		for(int i=0;i<3;i++) {
			System.out.print((char)(res[i][0]%26+97));
			res[i][0]=res[i][0];
		}
		for(int i=0;i<3;i++){
			for(int j=0;j<1;j++){
				for(int k=0;k<3;k++) {
					decrypt[i][j] = decrypt[i][j]+b[i][k]*res[k][j]; 
				}
			}
		}
		System.out.print("\nDecrypted string is : ");
		for(int i=0;i<3;i++){
			System.out.print((char)(decrypt[i][0]%26+'a'));
		}
		System.out.print("\n");
	}
	public static void getkeymes() throws IOException {
		System.out.println("Enter 3x3 matrix for key (It should be inversible): ");
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				a[i][j] = sc.nextDouble();
			}
		}
		System.out.print("\nEnter a 3 letter string: ");
		String msg = br.readLine();
		for(int i=0;i<3;i++){
			mes[i][0] = msg.charAt(i)-97;
		}
	}
	public static boolean inverse(){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				b[j][i]=getCofactor(i,j);
				if((i+j)%2==1) b[j][i]*=-1;
			}
		}
		double D=determinant();
		if(Double.compare(D,0.0d)==0){
			return false;
		}
		else{
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					b[i][j]/=D;
				}
			}
			return true;
		}
	}
	static double getCofactor(int p, int q)
	{
	    int i = 0, j = 0;
	    double[][] temp= new double[2][2];
	    for (int row = 0; row < N; row++)
	    {
	        for (int col = 0; col < N; col++)
	        {
	            if (row != p && col != q)
	            {
	                temp[i][j++] = a[row][col];
	                if (j == N - 1)
	                {
	                    j = 0;
	                    i++;
	                }
	            }
	        }
	    }
	    return ((temp[0][0]*temp[1][1])-(temp[0][1]*temp[1][0]));
	}
	static double determinant()
	{
	    double D = 0;
	    for(int col=0;col<N;col++){
	    	if(col%2==1){
	    		D+=(a[0][col]*getCofactor(0,col)*-1);
	    	}
	    	else D+=(a[0][col]*getCofactor(0,col));
	    }
	    return D;
	}
}