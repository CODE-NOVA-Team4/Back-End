package Kuitso.demo.service;

import com.example.likelion12.common.exception.CrewException;
import com.example.likelion12.common.exception.SocialringException;
import com.example.likelion12.domain.Socialring;
import com.example.likelion12.dto.Crew;
import com.example.likelion12.dto.HomeResponse;
import com.example.likelion12.repository.CrewRepository;
import com.example.likelion12.repository.SocialringRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.likelion12.common.response.status.BaseExceptionResponseStatus.CANNOT_FOUND_CREW_LIST;
import static com.example.likelion12.common.response.status.BaseExceptionResponseStatus.CANNOT_FOUND_SOCIALRING;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService {

}
