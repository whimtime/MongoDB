package com.SocialWEB.classi.test;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;

import com.SocialWEB.classi.CostruzioneHTML;

public class CostruzioneHTMLTest {

	@Test
	public void test() throws UnknownHostException 
	{
		CostruzioneHTML test = new CostruzioneHTML();
		test.GetDocumentoFinale("tiziano");
	}

}
