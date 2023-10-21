package me.jincrates.global.common.file.adapter;

import me.jincrates.global.common.file.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FileRepository extends JpaRepository<Attachment, Long> {
}
