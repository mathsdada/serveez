package com.loopkillers.serveez.service;

import com.loopkillers.serveez.exception.DataValidationException;
import com.loopkillers.serveez.exception.ResourceNotFoundException;
import com.loopkillers.serveez.model.MaidMaster;
import com.loopkillers.serveez.model.User;
import com.loopkillers.serveez.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaidMasterService {
    @Autowired
    private MaidMasterRepository mBookRepository;

    @Autowired
    private UserRepository mUserRepository;

    private User findUser(Long id) {
        User user = mUserRepository.findById(id).orElse(null);
        if (user == null) throw new ResourceNotFoundException(String.format("User with ID = %s does not exist", id));
        return user;
    }

    private void save(MaidMaster book) {
        try {
            mBookRepository.save(book);
        } catch (DataIntegrityViolationException ex) {
            String exMsg = ex.getMostSpecificCause().getMessage();
            if (exMsg.contains("uk_master_id_maid_id")) {
                exMsg = String.format("Maid = %s and Master = %s association exist",
                        book.getMaid().getId(), book.getMaster().getId());
            } else if (exMsg.contains("uk_maid_id")) {
                exMsg = String.format("Maid = %s already associated with a master", book.getMaid().getId());
            }
            throw new DataValidationException(exMsg);
        }
    }

    public void addMaidMasterEntry(Long maidId, Long masterId) {
        User maid = findUser(maidId);
        if (maid.getUserType() != UserType.MAID) {
            throw new DataValidationException("Maid is not registered as type MAID");
        }
        User master = findUser(masterId);
        if (master.getUserType() != UserType.MAID_MASTER) {
            throw new DataValidationException("Master is not registered as type MASTER");
        }
        save(new MaidMaster(maid, master));
    }

    public void removeMaidMasterEntry(Long maidId, Long masterId) {
        MaidMaster book = mBookRepository.findByMaidIdAndMasterId(maidId, masterId).orElse(null);
        if (book == null) {
            throw new DataValidationException(
                    String.format("Maid = %s and Master = %s association does not exist", maidId, masterId));
        }
        mBookRepository.delete(book);
    }

    public List<User> getMaidsByMasterId(Long masterId) {
        List<User> maids = new ArrayList<>();
        List<MaidMaster> maidMasters = mBookRepository.findByMasterId(masterId);
        for (MaidMaster maidMaster: maidMasters) {
            maids.add(maidMaster.getMaid());
        }
        return maids;
    }
}
