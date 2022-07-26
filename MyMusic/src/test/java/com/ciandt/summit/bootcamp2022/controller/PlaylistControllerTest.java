package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.controller.request.PlaylistRequest;
import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.model.ArtistaDTO;
import com.ciandt.summit.bootcamp2022.model.MusicaDTO;
import com.ciandt.summit.bootcamp2022.service.PlaylistServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(PlaylistController.class)
public class PlaylistControllerTest {

    @MockBean
    PlaylistServiceImp playlistServiceImp;

    @Autowired
    private MockMvc mvc;

    @Test
    void test_adicionarMusica() throws Exception {

        MusicaDTO mR1 = new MusicaDTO("b97e179d-76f1-44bb-a04f-1d678c1269ff", "Marseilles", new ArtistaDTO("771bc41f-20dd-418b-9df1-5b01e8cf0658","Brian Eno"));
        PlaylistRequest pR1 = new PlaylistRequest(mR1);
        Playlist p1 = new Playlist("cb746719-b60e-4c38-9976-f2cbc68581cb");

        PlaylistMusica playlistMusica = new PlaylistMusica(new PlaylistMusicaKey(p1.getId(), pR1.getData().getId()));

        given(playlistServiceImp.adicionarMusicaNaPlaylist(
                "cb746719-b60e-4c38-9976-f2cbc68581cb", pR1)).willReturn(playlistMusica);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(pR1);

        RequestBuilder request = post("/api/playlists/cb746719-b60e-4c38-9976-f2cbc68581cb/musicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        MvcResult result = mvc.perform(request).andExpect(status().isCreated()).andReturn();

        assertEquals("",
                result.getResponse().getContentAsString());
    }

    @Test
    void test_removerMusica() throws Exception {

        String playlist = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
        String musica = "c96b8f6f-4049-4e6b-8687-82e29c05b735";

        playlistServiceImp.removerMusicaFromPlaylist(playlist, musica);

        RequestBuilder request = delete("/api/playlists/92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed/musicas/c96b8f6f-4049-4e6b-8687-82e29c05b73");

        MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();

        assertEquals("",
                result.getResponse().getContentAsString());

    }
}