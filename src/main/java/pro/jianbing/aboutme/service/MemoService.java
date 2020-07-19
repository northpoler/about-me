package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.repository.MemoRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author DefaultAccount
 */
@Service
public class MemoService {

    private final
    MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public List<Memo> getMemoList(Long userId){
        return memoRepository.findAllByUserIdOrderBySequenceAsc(userId);
    }

    public void delete(Long id, Long userId){
        // 更新其他链接的排序
        Memo memo = getById(id);
        memoRepository.decreaseSequence(memo.getSequence(),userId);
        memoRepository.deleteById(id);
    }

    public long getMaxSequence(Long userId){
        return memoRepository.countByUserId(userId);
    }

    @Transactional
    public Integer save(Memo memo){
        // 更新其他链接的排序
        memoRepository.increaseSequence(memo.getSequence(), memo.getUserId());
        // 保存
        Memo save = memoRepository.save(memo);
        if (save!=null){
            return 1;
        }
        return 0;
    }

    @Transactional
    public Integer update(Memo memo){
        // 更新其他链接的排序
        Memo old = getById(memo.getId());
        if (old.getSequence()>memo.getSequence()){
            memoRepository.increaseSequence(memo.getSequence(),old.getSequence(),memo.getUserId());
        } else if (old.getSequence()<memo.getSequence()){
            memoRepository.decreaseSequence(old.getSequence(),memo.getSequence(),memo.getUserId());
        }
        // 保存
        Memo save = memoRepository.save(memo);
        if (save!=null){
            return 1;
        }
        return 0;
    }

    public Memo getOld(Long id){
        return getById(id);
    }

    private Memo getById(Long id){
        return memoRepository.findById(id).get();
    }
}
