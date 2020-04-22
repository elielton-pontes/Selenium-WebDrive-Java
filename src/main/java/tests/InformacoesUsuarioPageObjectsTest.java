package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {
		
	private WebDriver navegador;
	
	@Before
	public void setUp() {
		navegador = Web.createBrowserStack();
	}
	
	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(
			@Param(name="login")String login,
			@Param(name="senha")String senha,
			@Param(name="tipo")String tipo,
			@Param(name="contato")String contato,
			@Param(name="mensagem")String mensagemEsperada)
	{
		String textoToast = new LoginPage(navegador)
		.clicarSignIn()
		.fazerLogin(login, senha)
		.clicarMe()
		.clicarAbaMoreDataAboutYou()
		.clicarBotaoaddMoreDataAboutYou()
		.adicionarContato("Phone", "+5511999998888")
		.capturarTextoToast();
		
        assertEquals("Your contact has been added!", textoToast);

		
		
	}
	
	@After
	public void tearDown() {
		navegador.quit();
	}
}
