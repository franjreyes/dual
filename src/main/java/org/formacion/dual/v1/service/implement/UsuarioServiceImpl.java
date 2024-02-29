package org.formacion.dual.v1.service.implement;

import org.formacion.dual.v1.exception.ImagenException;
import org.formacion.dual.v1.exception.UsuarioException;
import org.formacion.dual.v1.persistence.model.Usuario;
import org.formacion.dual.v1.persistence.repository.UsuarioRepository;
import org.formacion.dual.v1.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repositorio;

    public UsuarioServiceImpl(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario save(Usuario usuario) throws ImagenException {
        /*if (usuario.getImagen() == null || usuario.getImagen().length() == 0) {
            throw new ImagenException("La imagen para el usuario " + usuario.getNombre() + " no existe");
        }*/
        return repositorio.save(usuario);
    }

    public Usuario get(UUID usuario_id) {
        return repositorio.findById(usuario_id).orElse(null);
    }

    public Page<Usuario> getAll(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    public Optional<Usuario> getByName(String usuarioName) {
    	return repositorio.findByNombreIgnoreCase(usuarioName);
    }

    @Override
    public Usuario save(Usuario entity, UUID id) throws UsuarioException, ImagenException {
        Usuario dbo = repositorio.findById(id).orElseThrow(()->new UsuarioException("No existe el usaurio con ID " + id));
        entity.setId(dbo.getId());
        if (entity.getImagen() == null || entity.getImagen().length() == 0) {
            throw new ImagenException("La imagen para el usuario con id " + id + " no existe");
        }
        return repositorio.save(entity);
    }

    @Override
    public void delete(UUID id) {
        repositorio.deleteById(id);
    }
}
