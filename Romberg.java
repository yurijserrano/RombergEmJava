package org.mack.an2.ativ1;

/**
 * @author Yuri Serrano
 *
 */

public abstract class Romberg {
	/**
	 * a,b intervalo de integração
	 * n   número de linhas do Romberg
	 */
	private double a;
	private double b;
	private int n;

	Romberg(double a, double b, int n) {
		this.a = a;
		this.b = b;
		this.n = n;
	}
	/**
	 * Função a ser integrada
	 * @param x
	 * @return
	 */
	abstract double f(double x);
	/**
	 * Calcula x elevado a i
	 * @param x int
	 * @param i potência inteira
	 * @return x elevado a i
	 */
	private static int expo(int x, int i) {
		int res = 1;
		while (i>0) {
			res *= x;
			i--;
		}
		return res;
	}
	/**
	 * Calcula Romberg e coloca o resultado numa String
	 */
	public String toString() {
		String s="Exercício 4\n";
		double R[] = new double[(n+1)*n/2];
		int indice=0;
		double hh=b-a;
		R[indice]=(b-a)*(f(a)+f(b))/2;	// linha 1
		s += "R11=" + R[indice++] + "\n";
		for (int k=2;k<=n;k++) {	// linha k
			hh /= 2;
			R[indice]=R[indice-k+1];	// coluna 1 (R[k][1]
			for (int i=1;i<=expo(2,k-2);i++) {
				double hhh=hh*2;		// hk[-1]
				R[indice]+=hhh*f(a+hh*(2*i-1));
			}
			R[indice]/=2;
			s += "R" + k + "1=" + R[indice++];
			for (int j=2;j<=k;j++) {
				R[indice]=R[indice-1]+(R[indice-1]-R[indice-k])/(expo(4,j-1)-1);
				s += "\tR" + k + j + "=" + R[indice++];
			}
			s += "\n";
		}
		return s;
	} 
}
