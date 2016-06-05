package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Coach;
import domain.Match;
import domain.Recruitment;
import domain.Summary;
import repositories.MatchRepository;

@Service
@Transactional
public class MatchService {

	// Managed repository
	@Autowired
	private MatchRepository matchRepository;
	// Services

	@Autowired
	private CoachService coachService;
	
	@Autowired
	private RecruitmentService recruitmentService;

	@Autowired
	private SummaryService summaryService;

	// Constructor
	public MatchService() {
		super();
	}

	public Match create() {
		Match m = new Match();

		return m;
	}

	public void save(Match m) {
		Assert.notNull(m);
		Date date = new Date();
		date.setSeconds(date.getSeconds() + 1);
		Assert.isTrue(m.getMoment().after(date));
		Coach c = coachService.findOne(coachService.findByPrincipal().getId());

		m.setCoach(c);

		matchRepository.save(m);
	}

	public void delete(Match m) {
		Assert.notNull(m);
		if ( m.getRecruitment()!=null){
			Recruitment r = recruitmentService.findOne(m.getRecruitment().getId());
			recruitmentService.delete(r);
		}
		matchRepository.delete(m);
	}

	public Match findOne(int id) {
		Match m = matchRepository.findOne(id);

		return m;
	}

	public Collection<Match> findFuture() {
		Collection<Match> matches = matchRepository.findAll();
		Collection<Match> future = new ArrayList<Match>();
		Date date = new Date();

		for (Match m : matches) {
			if (m.getMoment().after(date))
				future.add(m);

		}

		return future;

	}

	public Collection<Match> findPast() {

		Collection<Match> matches = matchRepository.findAll();
		Collection<Match> past = new ArrayList<Match>();
		Collection<Summary> summaries = summaryService.findAll();
		Date date = new Date();
		for (Match m : matches) {
			if (m.getMoment().before(date) && !past.contains(m)){
				past.add(m);
			}
			for (Summary s : summaries) {
				if (s.getMatch().getId() == m.getId()) {
					m.setSummary(s);
				}
			}
		}

		return past;

	}

	public Collection<Match> findAll() {
		return matchRepository.findAll();
	}

}
