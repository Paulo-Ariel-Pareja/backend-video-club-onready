package com.adndevelopersoftware.app.service.impl;

import com.adndevelopersoftware.app.dao.PeliculaDao;
import com.adndevelopersoftware.app.entity.Pelicula;
import com.adndevelopersoftware.app.service.PeliculaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

  private final PeliculaDao peliculaDao;
  private final ModelMapper modelMapper;

  public PeliculaServiceImpl(PeliculaDao peliculaDao, ModelMapper modelMapper) {
    this.peliculaDao = peliculaDao;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional(readOnly = true)
  public Pelicula findById(Long id) {
    return peliculaDao.findById(id).get();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Pelicula> findAll(Pageable pageable) {
    return peliculaDao.findAllByOrderByNombreAsc(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Pelicula> findByNombre(String nombre) {
    return peliculaDao.findByNombreIgnoreCaseOrderByNombre(nombre);
  }

  @Override
  @Transactional
  public Pelicula save(Pelicula movie) {
    return peliculaDao.save(movie);
  }

  @Override
  @Transactional
  public Pelicula update(Pelicula movie) {
    Assert.notNull(movie, "La película no puede ser nula");
    Assert.notNull(movie.getId(), "El id de la película no puede ser nulo");
    Pelicula movieToUpdate = findById(movie.getId());
    modelMapper.map(movie, movieToUpdate);
    return movieToUpdate;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Assert.notNull(id, "El id de la película no puede ser nulo");
    peliculaDao.deleteById(id);
  }

}
