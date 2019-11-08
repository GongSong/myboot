package com.yh.kuangjia.test.ES;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public class ItemRepositoryImpl implements ElasticsearchRepository<Item,Long> {

    @Override
    public <S extends Item> S index(S s) {
        return null;
    }

    @Override
    public <S extends Item> S indexWithoutRefresh(S s) {
        return null;
    }

    @Override
    public Iterable<Item> search(QueryBuilder queryBuilder) {
        return null;
    }

    @Override
    public Page<Item> search(QueryBuilder queryBuilder, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Item> search(SearchQuery searchQuery) {
        return null;
    }

    @Override
    public Page<Item> searchSimilar(Item item, String[] strings, Pageable pageable) {
        return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    public Class<Item> getEntityClass() {
        return null;
    }

    @Override
    public Iterable<Item> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Item> S save(S s) {
        return null;
    }

    @Override
    public <S extends Item> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Item> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Item> findAll() {
        return null;
    }

    @Override
    public Iterable<Item> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Item item) {

    }

    @Override
    public void deleteAll(Iterable<? extends Item> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
