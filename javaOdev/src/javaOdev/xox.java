package javaOdev;

import java.util.Arrays;
import java.util.Scanner;

public class xox {
	static int sira = 0;
	static int satir, sutun;
	static char tabloDegeri[][] = { { '*', '*', '*' }, { '*', '*', '*' }, { '*', '*', '*' } }; // oyun tahtasına boş
																								// değerleri atıyoruz

	public static void main(String[] args) {

		System.out.println("1. Oyuncu = X ");
		System.out.println("2. Oyuncu = O ");

		Scanner input = new Scanner(System.in);
		Kontrol kontrol = new Kontrol();
		OyunBittiMi bittiMi = new OyunBittiMi();
		do {
			oyunTablosu(tabloDegeri);
			do {
				System.out.println();
				System.out.println("Sira  " + (sira + 1) + ". oyuncuda\n");
				System.out.print("Kacinci satira yazmak istiyorsunuz?");
				satir = input.nextInt();
				System.out.print("Kacinci sutuna yazmak istiyorsunuz?");
				sutun = input.nextInt();
				System.out.println("--------------------------------------");
			} while (satir < 1 || satir > 3 || sutun < 1 || sutun > 3 // girilen istenmeyen değerlerin kontolü
					|| kontrol.doluBosKontrol(satir - 1, sutun - 1, tabloDegeri));
			if (sira == 0) { // kullanıcının girdiği değeri atama
				tabloDegeri[satir - 1][sutun - 1] = 'X';
			} else {
				tabloDegeri[satir - 1][sutun - 1] = 'O';
			}
			sira = (sira == 0) ? 1 : 0; // sırayı değiştirme
		} while (!bittiMi.oyunBittiMi(tabloDegeri, sira));
		oyunTablosu(tabloDegeri);
	}

	private static void oyunTablosu(char x[][]) { // değerleri düzenli bir şekilde yazdırma
		System.out.println();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < x[i].length; j++) {
				System.out.print((j < x[i].length) ? "\t" : "");
				System.out.print(x[i][j]);
			}
			System.out.println();
		}
	}
}

class Kontrol {
	public boolean satirSutunCarprazKontrol(char x[][]) {
		String satirSonuc = "";
		String sutunSonuc = "";
		String soldanCarprazSonuc = "";
		String sagdanCarprazSonuc = "";
		int deger = 0;
		for (int i = 0; i < 3; i++) { // satır
			for (int j = 0; j < 3; j++) { // sutun
				satirSonuc = satirSonuc + (x[i][j]);
				sutunSonuc = sutunSonuc + x[j][i];
				if (i == j) {
					soldanCarprazSonuc = soldanCarprazSonuc + x[i][j];
				}
				if ((i + j) == 2) {
					sagdanCarprazSonuc = sagdanCarprazSonuc + x[i][j];
				}
			}
			if (satirSonuc.equalsIgnoreCase("XXX") || satirSonuc.equalsIgnoreCase("OOO")
					|| sutunSonuc.equalsIgnoreCase("XXX") || sutunSonuc.equalsIgnoreCase("OOO")
					|| soldanCarprazSonuc.equalsIgnoreCase("XXX") || soldanCarprazSonuc.equalsIgnoreCase("OOO")
					|| sagdanCarprazSonuc.equalsIgnoreCase("XXX") || sagdanCarprazSonuc.equalsIgnoreCase("OOO")) {
				deger++;
				break;
			}
			satirSonuc = "";
			sutunSonuc = "";
		}
		if (deger == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean doluBosKontrol(int satir, int sutun, char dizi[][]) { // girilmek istenen yer dolu mu
		char t = '*';
		if (dizi[satir][sutun] == t) {
			return (false);
		} else {
			return (true);
		}
	}
}

class OyunBittiMi extends Kontrol { // üst sınıftan gelen bilgiye göre sonucu verir
	public boolean oyunBittiMi(char x[][], int sira) {
		if (satirSutunCarprazKontrol(x)) {
			if (sira == 1) {
				System.out.println("\tKAZANAN ==> (X) 1.Oyuncu !!!");
				return true;
			} else {
				System.out.println("\tKAZANAN ==> (O) 2.Oyuncu !!!");
				return true;
			}
		}
		String ifade = Arrays.deepToString(x);
		if (!ifade.contains("*")) {
			System.out.println("\tOyun BERABERE !");
			return true;
		}
		return false;
	}
}