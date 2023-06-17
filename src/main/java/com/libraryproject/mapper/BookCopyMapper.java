package com.libraryproject.mapper;

import com.libraryproject.domain.BookCopy;
import com.libraryproject.domain.dto.BookCopyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyMapper {

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy){
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(List<BookCopy> bookCopyList){
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
