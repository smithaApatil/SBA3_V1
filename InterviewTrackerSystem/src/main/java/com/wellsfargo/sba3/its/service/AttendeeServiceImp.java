package com.wellsfargo.sba3.its.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.sba3.its.dao.AttendeeRepository;
import com.wellsfargo.sba3.its.dao.InterviewsRepository;
import com.wellsfargo.sba3.its.dao.UsersRepository;
import com.wellsfargo.sba3.its.entity.Attendee;
import com.wellsfargo.sba3.its.entity.Interviews;
import com.wellsfargo.sba3.its.entity.Users;
import com.wellsfargo.sba3.its.exceptions.UserException;


@Service
public class AttendeeServiceImp implements AttendeeService{

	@Autowired
	private AttendeeRepository attendeeRepo;
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private InterviewsRepository interviewRepo;
	
	@Override
	public Attendee addAttendee(int userId, int interviewId) throws UserException {
		Attendee attendee = new Attendee();
		if (interviewRepo.existsById(interviewId)) {
			if (userRepo.existsById(userId)) {
				int count = attendeeRepo.getInterviewAttendedByUser(userId, interviewId);
				if (count>0) {
					throw new UserException("Please check if already attended the interview"); 
				} else {
					attendee.setUserId(userId);
					attendee.setInterviewId(interviewId);
					attendeeRepo.save(attendee);
				}
			} else
				throw new UserException("User could not Found !!");		
		} else
			throw new UserException("Interview was  Not Found !!");
		return attendee;
	}


	@Override
	public ArrayList getUsersByInterviewID(int interviewId) throws UserException {
		List<Users> users = null;
		ArrayList al = new ArrayList();
		if (interviewRepo.existsById(interviewId)) {
			
			List<Attendee> attendee = attendeeRepo.findAllByInterviewId(interviewId);
			if (attendee.isEmpty())
				throw new UserException("Looks like the no user attended the interview! No users found");
			else {
				List<Integer> userIds = attendeeRepo.findAllUsersAttendedInterview(interviewId);				
				for (int i = 0;i<userIds.size();i++) {
					users=(userRepo.findAllByUserId(userIds.get(i)));
					al.add(users);
				}
			}
		} else {
			throw new UserException("Interview was  Not Found !!");
		}
		return al;
	}


	@Override
	public ArrayList getInterviewsByUserID(int userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	


}
