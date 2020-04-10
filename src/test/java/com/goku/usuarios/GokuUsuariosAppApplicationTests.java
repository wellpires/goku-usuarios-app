package com.goku.usuarios;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "message.error-generic = Erro interno!")
class GokuUsuariosAppApplicationTests {

	@Test
	void contextLoads() {
	}

}
