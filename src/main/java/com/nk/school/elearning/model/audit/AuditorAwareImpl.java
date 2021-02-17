package com.nk.school.elearning.model.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		try {
			return Optional.ofNullable("system_user");
			//return Optional.ofNullable(UserUtils.getCurrentUser().getUsername());
		} catch (NullPointerException e) {
			return Optional.ofNullable("system_user");
		}
	}
	
}
