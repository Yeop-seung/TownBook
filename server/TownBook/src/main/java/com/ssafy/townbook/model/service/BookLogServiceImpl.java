package hide information.townbook.model.service;

import hide information.townbook.model.dto.BookDto;
import hide information.townbook.model.dto.BookLogDto;
import hide information.townbook.model.dto.request.DonateBookRequestDto;
import hide information.townbook.model.dto.request.ReceiveBookRequestDto;
import hide information.townbook.model.dto.request.ReceiverWishListRequestDto;
import hide information.townbook.model.dto.response.DonateBookLogResponseDto;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.dto.response.ReceiveBookLogResponseDto;
import hide information.townbook.model.entity.Account;
import hide information.townbook.model.entity.BookLog;
import hide information.townbook.model.entity.DetailLocker;
import hide information.townbook.model.entity.Locker;
import hide information.townbook.model.entity.WishList;
import hide information.townbook.model.repository.AccountRepository;
import hide information.townbook.model.repository.AdminRepository;
import hide information.townbook.model.repository.BookLogRepository;
import hide information.townbook.model.repository.BookRepository;
import hide information.townbook.model.repository.DetailLockerRepository;
import hide information.townbook.model.repository.LockerRepository;
import hide information.townbook.model.repository.WishListRepository;
import hide information.townbook.queryrepository.BookLogQueryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookLogServiceImpl implements BookLogService {
    
    private BookLogRepository      bookLogRepository;
    private BookLogQueryRepository bookLogQueryRepository;
    private LockerRepository       lockerRepository;
    private DetailLockerRepository detailLockerRepository;
    private AdminRepository        adminRepository;
    private BookRepository         bookRepository;
    private AccountRepository      accountRepository;
    private WishListRepository     wishListRepository;
    
    @Autowired
    public BookLogServiceImpl(
            BookLogRepository bookLogRepository, BookLogQueryRepository bookLogQueryRepository,
            LockerRepository lockerRepository, DetailLockerRepository detailLockerRepository,
            AdminRepository adminRepository, BookRepository bookRepository, AccountRepository accountRepository,
            WishListRepository wishListRepository) {
        this.bookLogRepository      = bookLogRepository;
        this.bookLogQueryRepository = bookLogQueryRepository;
        this.lockerRepository       = lockerRepository;
        this.detailLockerRepository = detailLockerRepository;
        this.adminRepository        = adminRepository;
        this.bookRepository         = bookRepository;
        this.accountRepository      = accountRepository;
        this.wishListRepository     = wishListRepository;
    }
    
    /**
     * 전체 북로그 조회
     *
     * @return List<BookLogDto>
     */
    @Override
    public FindListResponseDto findAllBookLogs() {
        Optional<List<BookLog>> findBookLogs = Optional.ofNullable(bookLogRepository.findAll());
        return new FindListResponseDto(findBookLogs.get().stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList()));
    }
    
    /**
     * 단일 북로그 조회
     *
     * @param bookLogNo
     * @return BookLogDto
     */
    @Override
    public FindOneResponseDto findBookLogByBookLogNo(Long bookLogNo) {
        BookLog    bookLog    = bookLogRepository.findBookLogByBookLogNo(bookLogNo).get();
        BookLogDto bookLogDto = new BookLogDto(bookLog);
        return new FindOneResponseDto(bookLogDto);
    }
    
    /**
     * 단일 회원의 모든 북로그 조회
     *
     * @param accountNo
     * @return List<BookLog>
     */
    public FindListResponseDto findBookLogByAccountNo(Long accountNo) {
        List<BookLog> findBookLogs = bookLogQueryRepository.findBookLogByAccountNo(accountNo).get();
        return new FindListResponseDto(findBookLogs.stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList()));
    }
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return List<BookDto>
     */
    @Override
    public FindListResponseDto findBookLogByLockerNo(Long lockerNo) {
        List<BookLog>    findBookLogs       = bookLogQueryRepository.findBookLogByLockerNo(lockerNo).get();
        List<BookLogDto> findBookLogDtoList = findBookLogs.stream().map(BookLogDto::new).collect(Collectors.toList());
        
        JSONArray jsonArray = new JSONArray();
        for (BookLogDto bookLogDto : findBookLogDtoList) {
            JSONObject jsonObject     = new JSONObject();
            String     bookIsbn       = bookLogDto.getBookIsbn();
            BookDto    bookDto        = new BookDto(bookRepository.findBookByBookIsbn(bookIsbn).get());
            long       detailLockerNo = bookLogDto.getDetailLockerNo();
            long detailLockerNoInLocker = detailLockerRepository.findDetailLockerByDetailLockerNo(detailLockerNo)
                    .get().getDetailLockerNoInLocker();
            jsonObject.put("bookLog", bookLogDto);
            jsonObject.put("book", bookDto);
            jsonObject.put("detailLockerNoInLocker", detailLockerNoInLocker);
            jsonArray.add(jsonObject);
        }
        
        return new FindListResponseDto(jsonArray);
    }
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return List<BookLogDto.bookLogReview>
     */
    public FindListResponseDto findBookLogReviewByBookIsbn(String bookIsbn) {
        List<String> findReviews = bookLogQueryRepository.findBookLogReviewByBookIsbn(bookIsbn).get();
        return new FindListResponseDto(findReviews);
    }
    
    
    /**
     * 도서 기부
     *
     * @param donateBookRequestDto
     * @return AdminDto
     */
    @Override
    @Transactional
    public FindOneResponseDto donateBook(DonateBookRequestDto donateBookRequestDto) throws Exception {
        // 비어있는지 체크
        long         detailLockerNo = donateBookRequestDto.getDetailLockerNo();
        DetailLocker detailLocker   = detailLockerRepository.findDetailLockerByDetailLockerNo(detailLockerNo).get();
        if (detailLocker.getBookInDetailLocker() != null) { // 비어있지 않다면
            // return fail message
            String status = "보관함이 비어있지 않습니다.";
            return new FindOneResponseDto(status);
        } else {
            String bookIsbn  = donateBookRequestDto.getBookIsbn();
            String bookTitle = bookRepository.findBookByBookIsbn(bookIsbn).get().getBookTitle();
            detailLocker.setBookInDetailLocker(bookTitle);
        }
        
        // Account
        long    accountNo = donateBookRequestDto.getAccountNo();
        Account account   = accountRepository.findByAccountNo(accountNo).get();
        account.setAccountPoint(account.getAccountPoint() + 100);
        account.setAccountBookCnt(account.getAccountBookCnt() + 1);
        
        // Locker
        long   lockerNo = donateBookRequestDto.getLockerNo();
        Locker locker   = lockerRepository.findLockerByLockerNo(lockerNo).get();
        locker.setLockerBookCnt(locker.getLockerBookCnt() + 1);
        
        // Book
        String bookIsbn = donateBookRequestDto.getBookIsbn();
        
        // BookLog
        BookLog bookLog = new BookLog(lockerNo, detailLockerNo, accountNo, bookIsbn);
        bookLogRepository.save(bookLog);
        
        // return Point
        return new FindOneResponseDto(new DonateBookLogResponseDto(account.getAccountPoint()));
    }
    
    /**
     * 도서 수령
     *
     * @param receiveBookRequestDto
     * @return Boolean
     * @throws Exception
     */
    @Override
    @Transactional
    public FindOneResponseDto receiveBook(ReceiveBookRequestDto receiveBookRequestDto) throws Exception {
        long    bookLogNo = receiveBookRequestDto.getBookLogNo();
        String  status;
        BookLog bookLog   = bookLogRepository.findBookLogByBookLogNo(bookLogNo).get();
        if (!bookLog.getBookLogState()) {
            status = "잘못된 접근입니다.";
            return new FindOneResponseDto(new ReceiveBookLogResponseDto(status));
        }
        
        // Account
        long    accountNo = receiveBookRequestDto.getAccountNo();
        Account account   = accountRepository.findByAccountNo(accountNo).get();
        
        // 포인트가 부족한 경우
        if (account.getAccountPoint() < 200) {
            status = "포인트가 부족합니다.";
            return new FindOneResponseDto(new ReceiveBookLogResponseDto(status));
        } else {
            Integer accountPoint = account.getAccountPoint() - 200;
            account.setAccountPoint(accountPoint);
            accountRepository.save(account);
        }
        
        // locker
        long   lockerNo = receiveBookRequestDto.getLockerNo();
        Locker locker   = lockerRepository.findLockerByLockerNo(lockerNo).get();
        locker.setLockerBookCnt(locker.getLockerBookCnt() - 1);
        lockerRepository.save(locker);
        
        // detailLocker
        long         detailLockerNo = receiveBookRequestDto.getDetailLockerNo();
        DetailLocker detailLocker   = detailLockerRepository.findDetailLockerByDetailLockerNo(detailLockerNo).get();
        detailLocker.setBookInDetailLocker(null);
        detailLockerRepository.save(detailLocker);
        
        // bookLog
        bookLog.setBookLogReceiverNo(accountNo);
        bookLog.setBookLogReceiveDateTime(LocalDateTime.now());
        bookLog.setBookLogState(false);
        bookLogRepository.save(bookLog);
        
        return new FindOneResponseDto(new ReceiveBookLogResponseDto(account.getAccountPoint()));
    }
    
    /**
     * 책 찜 상태 변경
     *
     * @param receiverWishListRequestDto
     * @return Boolean
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = false)
    public FindOneResponseDto changeWishState(ReceiverWishListRequestDto receiverWishListRequestDto) throws Exception {
        Optional<WishList> findWishList = wishListRepository.findByBookLogNo(receiverWishListRequestDto.getBookLogNo());
        
        if (findWishList.isEmpty()) {
            WishList wishList = new WishList(receiverWishListRequestDto.getAccountNo(),
                    receiverWishListRequestDto.getBookLogNo());
            wishListRepository.save(wishList);
            return new FindOneResponseDto(true);
        } else {
            wishListRepository.delete(findWishList.get());
            return new FindOneResponseDto(true);
        }
    }
}
