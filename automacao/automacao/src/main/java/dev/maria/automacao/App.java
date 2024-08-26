package dev.maria.automacao;

import static org.junit.Assert.assertEquals;


import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    WebDriver driver;
    
    @Before
    public void setup() {
    	driver = new ChromeDriver();
    }
    
    @Test   
    public void procurarArlecchino() throws InterruptedException {
    	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    	driver.get("https://wiki.hoyolab.com");
    	String tituloPagina = driver.getTitle();
    	assertEquals(tituloPagina, "Welcome to HoYoWiki!");
    	WebElement barraPesquisa = driver.findElement(By.xpath("/html/body/div[1]/div/main/main/nav/div[1]/div[2]/div/input"));
    	barraPesquisa.sendKeys("Arlecchino");
    	barraPesquisa.click();
    	Thread.sleep(5000);
    	WebElement personagem = driver.findElement(By.xpath("/html/body/div[1]/div/main/main/nav/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/div[2]/p[1]/em"));
    	String nomePersonagem = personagem.getText();
    	assertEquals(nomePersonagem, "Arlecchino");
    	Thread.sleep(58000);
    }
    
    @After
    public void encerrar() {
    	driver.quit();
    };
    
}
