import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSADemo {

	// �ͻ��˷������
	public void Client2Server() {
		try {
			// ===============���ɹ�Կ��˽Կ����Կ�����ͻ��ˣ�˽Կ����˱���==================
			System.out.println("�ͻ��ˣ���Կ���ܣ����͸�����ˣ�˽Կ���ܣ�----------------------------------------------------");
			// ����RSA��Կ��˽Կ����Base64����
			KeyPair keyPair = RSAUtil.getKeyPair(1024);
			String publicKeyStr = RSAUtil.getPublicKey(keyPair);
			String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
			System.out.println("RSA��ԿBase64����:" + publicKeyStr);
			System.out.println("RSA˽ԿBase64����:" + privateKeyStr);

			// =================�ͻ���=================
			// hello, i am infi, good night!����
			String message = "hello, i am infi, good night!";
			// ��Base64�����Ĺ�Կת����PublicKey����
			PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
			// �ù�Կ����
			byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
			// ���ܺ������Base64����
			String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
			System.out.println("��Կ���ܲ�Base64����Ľ����" + byte2Base64);

			// ############## �����ϴ����������Base64�����Ĺ�Կ �� Base64�����Ĺ�Կ���ܵ����� #################

			// ===================�����================
			// ��Base64������˽Կת����PrivateKey����
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			// ���ܺ������Base64����
			byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
			// ��˽Կ����
			byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
			// ���ܺ������
			System.out.println("���ܺ������: " + new String(privateDecrypt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����˻ؿͻ���
	public void Server2Client() {
		try {
			// ===============���ɹ�Կ��˽Կ����Կ�����ͻ��ˣ�˽Կ����˱���==================
			System.out.println("����ˣ�˽Կǩ���������ͻ��ˣ���Կ��ǩ��----------------------------------------------------");
			// ����RSA��Կ��˽Կ����Base64����
			KeyPair keyPair = RSAUtil.getKeyPair(1024);
			String publicKeyStr = RSAUtil.getPublicKey(keyPair);
			String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
			System.out.println("RSA��ԿBase64����:" + publicKeyStr);
			System.out.println("RSA˽ԿBase64����:" + privateKeyStr);

			// =================�����=================
			// hello, i am infi, good night!����
			String message = "hello, i am infi, good night!";
			// ��Base64������˽Կת����PrivateKey����
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			// ��˽Կǩ��
			byte[] privateSignature = RSAUtil.privateEncrypt(message.getBytes(), privateKey);
			// ���ܺ������Base64����
			String byte2Base64 = RSAUtil.byte2Base64(privateSignature);
			System.out.println("˽Կǩ����Base64����Ľ����" + byte2Base64);

			// ############## �����ϴ���������� Base64������˽Կǩ�������� #################

			// ===================�ͻ���================

			// ��Base64�����Ĺ�Կת����PublicKey����
			PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
			// ���ܺ������Base64����
			byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
			// �ù�Կ��ǩ
			byte[] publicSignature = RSAUtil.publicDecrypt(base642Byte, publicKey);
			System.out.println("�ù�Կ��ǩ�Ľ����" + new String(publicSignature));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RSADemo demo = new RSADemo();
		//
		demo.Client2Server();
		demo.Server2Client();
	}

}
