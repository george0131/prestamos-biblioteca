package com.serfinanzas.prestamos.service;

import com.serfinanzas.prestamos.persistence.repository.BookRepository;
import com.serfinanzas.prestamos.persistence.repository.LendItemRepository;
import com.serfinanzas.prestamos.persistence.repository.LendRepository;
import com.serfinanzas.prestamos.persistence.repository.ReaderInfoRepository;
import com.serfinanzas.prestamos.persistence.domain.*;
import com.serfinanzas.prestamos.rest.domain.LendInput;
import com.serfinanzas.prestamos.rest.domain.ReaderInfoInput;
import com.serfinanzas.prestamos.service.exception.LendException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class LendServiceImpl implements LendService {

    private final LendRepository repository;
    private final LendItemRepository lendItemRepository;
    private final ReaderInfoRepository readerInfoRepository;
    private final BookRepository bookRepository;

    public LendServiceImpl(LendRepository repository, LendItemRepository lendItemRepository, ReaderInfoRepository readerInfoRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.lendItemRepository = lendItemRepository;
        this.readerInfoRepository = readerInfoRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Lend createLend(LendInput input) {

        ReaderInfoInput readerInfoInput = input.getReaderInfo();

        ReaderInfo readerInfoFound = readerInfoRepository.findByNumberId(readerInfoInput.getDocumentId());

        if (readerInfoFound == null) {

            ReaderInfo newRow = new ReaderInfo(
                    readerInfoInput.getDocumentId(),
                    readerInfoInput.getNames(),
                    readerInfoInput.getLastNames(),
                    readerInfoInput.getBirthDate(),
                    readerInfoInput.getCellPhone()
            );

            try {
                readerInfoFound = readerInfoRepository.saveAndFlush(newRow);
            } catch (Exception e) {
                e.printStackTrace();
                throw new LendException(
                        "Ocurrió un error almacenando la información del lector.",
                        500
                );
            }
        }

        Book bookFound = bookRepository.findByIdLib(input.getLendItem().getBook().getCode());

        if (bookFound == null)
            throw new LendException(
                    String.format("El libro con IDLIB %s no existe", input.getLendItem().getBook().getCode()),
                    404
            );

        Lend bookIsInLoan = repository.checkIfBookIsInLoan(bookFound.getId());

        if (bookIsInLoan != null)
            throw new LendException(
                    String.format("El libro %s se encuentra prestado. La fecha de devolución es %s",
                            bookFound.getName(),
                            bookIsInLoan.getReturnOn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    ),
                    500
            );

        if (checkIfNumberIsPalindrome(input.getLendItem().getBook().getCode()))
            throw new LendException(
                    "Los libros palíndromos solo se pueden utilizar en la biblioteca.",
                    500
            );

        LocalDate returnOn = sumDigitsFromIDLib(input.getLendItem().getBook().getCode()) > 40
                ? LocalDate.now().plusDays(5)
                : input.getReturnOn();

        returnOn = returnOn.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                ? returnOn.plusDays(1)
                : returnOn;

        Lend newRow = new Lend(
                input.getUsername(),
                readerInfoFound,
                LocalDate.now(),
                returnOn
        );

        Lend lendCreated = null;
        try {
            lendCreated = repository.save(newRow);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LendException(
                    "Ocurrió un error creando el préstamo.",
                    500
            );
        }

        LendItem item = new LendItem(lendCreated, bookFound);

        try {
            lendItemRepository.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LendException(
                    "Ocurrió un error vinculando el libro al prestamo.",
                    500
            );
        }

        return lendCreated;
    }

    @Override
    public Page<Lend> getAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    private boolean checkIfNumberIsPalindrome(int num) {

        int reversedNum = 0, remainder;

        // store the number to originalNum
        int originalNum = num;

        // get the reverse of originalNum
        // store it in variable
        while (num != 0) {
            remainder = num % 10;
            reversedNum = reversedNum * 10 + remainder;
            num /= 10;
        }

        return originalNum == reversedNum; // true palindrome.
    }

    private int sumDigitsFromIDLib(int num) {

        int sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }

        return sum;
    }
}
