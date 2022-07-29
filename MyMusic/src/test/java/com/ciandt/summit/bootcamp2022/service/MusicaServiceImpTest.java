package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Artista;
import com.ciandt.summit.bootcamp2022.entity.Musica;
import com.ciandt.summit.bootcamp2022.exception.FiltroInvalidoException;
import com.ciandt.summit.bootcamp2022.exception.MusicaNaoExisteException;
import com.ciandt.summit.bootcamp2022.repository.ArtistaRepository;
import com.ciandt.summit.bootcamp2022.repository.MusicaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MusicaServiceImpTest {

    @Mock
    MusicaRepository musicaRepository;

    @Mock
    ArtistaRepository artistaRepository;

    @InjectMocks
    MusicaServiceImp musicaServiceImp;

    @Test
    void test_ordernarMusicasCorretamente(){
        Artista a1 = new Artista("A1", "Bruno Mars");
        Artista a2 = new Artista("A2", "Alana");
        Musica m1 = new Musica("M1", "Vixe", a1);
        Musica m2 = new Musica("M2", "Zorra", a1);
        Musica m3 = new Musica("M3", "Agua", a2);

        LinkedHashSet<Musica> musicas = new LinkedHashSet<Musica>();

        musicas.add(m2);
        musicas.add(m3);
        musicas.add(m1);

        List<Musica> results = musicaServiceImp.ordernarMusicas(musicas);

        assertEquals(3, results.size());
        for(int i = 0; i < 3; i++){
            if(i == 0)
                assertEquals(m3, results.get(i));
            if(i == 1)
                assertEquals(m1, results.get(i));
            if(i == 2)
                assertEquals(m2, results.get(i));
        }
    }

    @Test
    void test_buscarMusicasOrdenadas(){
        Artista a1 = new Artista("A1", "Bruno Mars");
        Artista a2 = new Artista("A2", "Alana");
        Musica m1 = new Musica("M1", "Vixe", a1);
        Musica m2 = new Musica("M2", "Zorra", a1);
        Musica m3 = new Musica("M3", "Agua", a2);

        given(musicaRepository.findByNomeContaining("Mars")).willReturn(Collections.emptyList());
        given(artistaRepository.findByNomeContaining("Mars")).willReturn(new ArrayList<Artista>(List.of(a1)));
        given(musicaRepository.findByArtista(a1)).willReturn(new ArrayList<Musica>(List.of(m1, m2)));

        List<Musica> expected = new ArrayList<Musica>(List.of(m1,m2));

        assertEquals(expected, musicaServiceImp.buscarMusicas("Mars"));
    }

    @Test
    void test_filtroInvalidoException(){
        try{
            musicaServiceImp.buscarMusicas("1");
            fail("Esta faltando a excecao");
        }catch(FiltroInvalidoException ex){
            assertEquals(FiltroInvalidoException.class, ex.getClass());
            assertEquals("1", ex.getMessage());
        }
    }

    @Test
    void test_buscaPlaylistIdCorreto(){
        Musica m1 = new Musica("b97e179d-76f1-44bb-a04f-1d678c1269ff", "Marseilles", new Artista("771bc41f-20dd-418b-9df1-5b01e8cf0658","Brian Eno"));

        given(musicaRepository.findById(m1.getId())).willReturn(Optional.of(m1));

        assertEquals(m1, musicaServiceImp.buscarMusicaPorId(m1.getId()));
    }

    @Test
    void test_buscaPlaylistIdIncorreto(){
        Musica m1 = new Musica("b97e179d-76f1-44bb-a04f-1d678c1269ff", "Marseilles", new Artista("771bc41f-20dd-418b-9df1-5b01e8cf0658","Brian Eno"));

        assertThrows(MusicaNaoExisteException.class, () -> musicaServiceImp.buscarMusicaPorId(m1.getId()));
    }
}