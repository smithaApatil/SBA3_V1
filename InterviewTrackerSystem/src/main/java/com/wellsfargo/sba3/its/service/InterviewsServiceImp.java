package com.wellsfargo.sba3.its.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.sba3.its.dao.InterviewsRepository;
import com.wellsfargo.sba3.its.entity.Interviews;
import com.wellsfargo.sba3.its.exceptions.UserException;



@Service
public class InterviewsServiceImp implements InterviewsService{
	@Autowired
	private InterviewsRepository interviewRepo;
	
	@Override
	@Transactional
	public Interviews add(Interviews interview) throws UserException{
		if(interview!=null) {
			if(interviewRepo.existsById(interview.getInterviewId())) {
				throw new UserException("Interview ID already  IN use");
			}
			
			interviewRepo.save(interview);
		}
		return interview;
	}

	@Override
	public Interviews save(Interviews interview) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean deleteInterview(int interviewId) throws UserException {
		if(!interviewRepo.existsById(interviewId)) {
			throw new UserException("InterviewID  is not Found");
		}
	
		interviewRepo.deleteById(interviewId);
		return true;
	}


	@Override
	public List<Interviews> getAllInterviews() throws UserException {
		return interviewRepo.findAll();
	}
	
	@Override
	public List<Interviews> getAllByInterviewName(String interviewName) throws UserException {
		List<Interviews> name =  interviewRepo.findAllByInterviewName(interviewName);
		if(name.isEmpty()) 
			throw new UserException("Interview name is not Found");
		return name;
	}
	
	@Override
	public List<Interviews> getAllByInterviewerName(String interviewerName) throws UserException {
		List<Interviews> name =  interviewRepo.findAllByInterviewerName(interviewerName);
		if(name.isEmpty()) 
			throw new UserException("Interviewer name is not Found");
			
		return name;
	}
	
	@Override
	public Interviews getInterviewById(int interviewId) throws UserException {
		return interviewRepo.findById(interviewId).orElse(null);		
	}

	@Override
	public Integer getTotalCount() throws UserException {
		return (int) interviewRepo.count();
	}
	
	

}
