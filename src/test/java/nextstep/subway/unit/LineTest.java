package nextstep.subway.unit;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.subway.applicaion.dto.LineRequest;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;

class LineTest {
	@DisplayName("구간 추가 테스트")
	@Test
	void addSection() {
		//given
		Line line = new Line("분당선", "yellow");
		Section section = new Section(line, new Station("서현역"), new Station("이매역"), 10);

		//when
		line.addSection(section);

		//then
		assertThat(line.getSections()).containsExactly(section);
	}

	@DisplayName("역 조회 테스트")
	@Test
	void getStations() {
		//given
		Line line = new Line("분당선", "yellow");
		Station upStation = new Station("서현역");
		Station downStation = new Station("이매역");
		line.addSection(new Section(line, upStation, downStation, 10));

		//when
		List<Station> stationList = line.getStations();

		//then
		assertThat(stationList).containsExactly(upStation, downStation);
	}

	@DisplayName("구간 삭제 테스트")
	@Test
	void removeSection() {
		//given
		Line line = new Line("분당선", "yellow");
		Section section = new Section(line, new Station("서현역"), new Station("이매역"), 10);
		line.addSection(section);

		//when
		line.removeSection(section);

		//then
		assertThat(line.getSections()).isEmpty();
	}

	@DisplayName("마지막 구간 정보 조회 테스트")
	@Test
	void getLastSectionTest() {

	    //given
		Line line = new Line("분당선", "yellow");
		Section section = new Section(line, new Station("서현역"), new Station("이매역"), 10);
		line.addSection(section);

	    //when
		Section lastSection = line.getLastSection();

	    //then
		assertThat(lastSection.getUpStation().getName()).isEqualTo("서현역");
		assertThat(lastSection.getDownStation().getName()).isEqualTo("이매역");
	 }
	 
	 @DisplayName("노선 정보 수정 테스트")
	 @Test
	 void updateLineTest() {
	 
	     //given
		 Line line = new Line("분당선", "yellow");
		 String updatedName = "신분당선";
		 String updatedColor = "red";
		 LineRequest updateReqDto = LineRequest.of(updatedName, updatedColor);

	     //when
	     line.update(updateReqDto);

	     //then
		 assertAll(
			 () -> assertThat(line.getName()).isEqualTo(updatedName),
			 () -> assertThat(line.getColor()).isEqualTo(updatedColor)
		 );
	  }
}
