import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSADemo {

	// 客户端发服务端
	public void Client2Server() {
		try {
			// ===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
			System.out.println("客户端（公钥加密）发送给服务端（私钥解密）----------------------------------------------------");
			// 生成RSA公钥和私钥，并Base64编码
			KeyPair keyPair = RSAUtil.getKeyPair(1024);
			String publicKeyStr = RSAUtil.getPublicKey(keyPair);
			String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
			System.out.println("RSA公钥Base64编码:" + publicKeyStr);
			System.out.println("RSA私钥Base64编码:" + privateKeyStr);

			// =================客户端=================
			// hello, i am infi, good night!加密
			String message = "hello, i am infi, good night!";
			// 将Base64编码后的公钥转换成PublicKey对象
			PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
			// 用公钥加密
			byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
			// 加密后的内容Base64编码
			String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
			System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);

			// ############## 网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容 #################

			// ===================服务端================
			// 将Base64编码后的私钥转换成PrivateKey对象
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			// 加密后的内容Base64解码
			byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
			// 用私钥解密
			byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
			// 解密后的明文
			System.out.println("解密后的明文: " + new String(privateDecrypt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 服务端回客户端
	public void Server2Client() {
		try {
			// ===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
			System.out.println("服务端（私钥签名）发给客户端（公钥验签）----------------------------------------------------");
			// 生成RSA公钥和私钥，并Base64编码
			KeyPair keyPair = RSAUtil.getKeyPair(1024);
			String publicKeyStr = RSAUtil.getPublicKey(keyPair);
			String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
			System.out.println("RSA公钥Base64编码:" + publicKeyStr);
			System.out.println("RSA私钥Base64编码:" + privateKeyStr);

			// =================服务端=================
			// hello, i am infi, good night!加密
			String message = "hello, i am infi, good night!";
			// 将Base64编码后的私钥转换成PrivateKey对象
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			// 用私钥签名
			byte[] privateSignature = RSAUtil.privateEncrypt(message.getBytes(), privateKey);
			// 加密后的内容Base64编码
			String byte2Base64 = RSAUtil.byte2Base64(privateSignature);
			System.out.println("私钥签名并Base64编码的结果：" + byte2Base64);

			// ############## 网络上传输的内容有 Base64编码后的私钥签名的内容 #################

			// ===================客户端================

			// 将Base64编码后的公钥转换成PublicKey对象
			PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
			// 加密后的内容Base64解码
			byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
			// 用公钥验签
			byte[] publicSignature = RSAUtil.publicDecrypt(base642Byte, publicKey);
			System.out.println("用公钥验签的结果：" + new String(publicSignature));
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
