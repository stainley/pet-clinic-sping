package com.salapp.petclinic.caching;

import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import com.salapp.petclinic.services.ClientService;
import com.salapp.petclinic.util.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
@ContextConfiguration
public class CachingTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    ClientRepository clientRepository;

    @Test
    public void caching() throws Exception {
        given(clientRepository.findClientById(anyLong())).willReturn(new Client("Test", Status.ACTIVE));

        clientService.getClientDetail(1L);
        clientService.getClientDetail(1L);

        verify(clientRepository, times(2)).findClientById(1L);
    }
}
