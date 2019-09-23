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

    public List<Memo> getMemoList(){
        return memoRepositoty.findAllByMarkOrderBySequenceAsc("0");
    }

    public void delete(Long id){
        // 更新其他链接的排序
        Memo memo = memoRepositoty.findById(id).get();
        memoRepositoty.decreaseSequence(memo.getSequence());
        memoRepositoty.deleteById(id);
    }

    public long count(){
        return memoRepositoty.count();
    }

    @Transactional
    public Integer save(Memo memo){
        // 更新其他链接的排序
        memoRepositoty.increaseSequence(memo.getSequence());
        // 保存
        Memo save = memoRepositoty.save(memo);
        if (save!=null){
            return 1;
        }
        return 0;
    }
}
