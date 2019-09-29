package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.repository.MemoRepositoty;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author DefaultAccount
 */
@Service
public class MemoService {

    private final
    MemoRepositoty memoRepositoty;

    @Autowired
    public MemoService(MemoRepositoty memoRepositoty) {
        this.memoRepositoty = memoRepositoty;
    }

    public List<Memo> getMemoList(Long userId){
        return memoRepositoty.findAllByUserIdOrderBySequenceAsc(userId);
    }

    public void delete(Long id, Long userId){
        // 更新其他链接的排序
        Memo memo = getById(id);
        memoRepositoty.decreaseSequence(memo.getSequence(),userId);
        memoRepositoty.deleteById(id);
    }

    public long count(){
        return memoRepositoty.count();
    }

    @Transactional
    public Integer save(Memo memo){
        // 更新其他链接的排序
        memoRepositoty.increaseSequence(memo.getSequence(), memo.getUserId());
        // 保存
        Memo save = memoRepositoty.save(memo);
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
            memoRepositoty.increaseSequence(memo.getSequence(),old.getSequence(),memo.getUserId());
        } else if (old.getSequence()<memo.getSequence()){
            memoRepositoty.decreaseSequence(old.getSequence(),memo.getSequence(),memo.getUserId());
        }
        // 保存
        Memo save = memoRepositoty.save(memo);
        if (save!=null){
            return 1;
        }
        return 0;
    }

    public Memo getOld(Long id){
        return getById(id);
    }

    private Memo getById(Long id){
        return memoRepositoty.findById(id).get();
    }
}
