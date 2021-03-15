package io.github.rafaelframos2016.tielerama.rest;

import io.github.rafaelframos2016.tielerama.model.entity.Cliente;
import io.github.rafaelframos2016.tielerama.model.entity.ServicoPrestado;
import io.github.rafaelframos2016.tielerama.model.repository.ClienteRepository;
import io.github.rafaelframos2016.tielerama.model.repository.ServicoPrestadoRepository;
import io.github.rafaelframos2016.tielerama.rest.dto.ServicoPrestadoDTO;
import io.github.rafaelframos2016.tielerama.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = Integer.valueOf(dto.getIdCliente());
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));


        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getValor()));

        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value="nome",required=false,defaultValue = "") String nome,
            @RequestParam(value="mes",required=false) Integer mes){

        return servicoPrestadoRepository.findByClienteNomeAndMes("%"+nome+"%",mes==null?Calendar.getInstance().get(Calendar.MONTH)+1:mes);

    }
}
