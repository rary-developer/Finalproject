package com.e_um.model.vo.placeinfo.movie.reserv;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.e_um.model.vo.placeinfo.movie.seat.Seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class MovieTiecketing {
	private String movieReservNum;
	private String userId;
	private Seat[] seats = new Seat[4];
	private String movieSeq, movieBox, movieLocation,movieTime;
	private int moviePrice;
	private Date movieReservDate;
	private String movieReservCancel;
}
