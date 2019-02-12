package ftn.upp.sc.converter;

public interface Converter<T,TDTO> {	
	
	public TDTO entityToDto(T entity);
	public T DtoToEntity(TDTO dto);
	
}
