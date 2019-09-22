package pro.jianbing.aboutme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.jianbing.aboutme.entity.Memo;
import pro.jianbing.aboutme.repository.MemoRepositoty;

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
        memoRepositoty.deleteById(id);
    }
}
