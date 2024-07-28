package com.espritech.workerExpress221.repository;

import com.espritech.workerExpress221.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<Worker> findByIdAndArchiveFalse(Long id);
    @Query("SELECT w FROM Worker w WHERE w.profession.name = :profession " +
            "AND (:region IS NULL OR :region = '' OR w.region.name = :region) " +
            "AND (:department IS NULL OR :department = '' OR w.department.name = :department) " +
            "AND w.isWorker = true")
    List<Worker> findAllByProfessionAndRegionAndDepartmentAndIsWorkerTrue(
            @Param("profession") String profession,
            @Param("region") String region,
            @Param("department") String department
    );

    List<Worker> findAllByArchiveFalseAndIsWorkerFalse();
    List<Worker> findAllByArchiveFalseAndIsWorkerTrue();
    Optional<Worker> findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(String phoneNumber);
    //Optional<Worker> findByPhoneNumberAndArchiveFalseAndIsWorkerTrue(String phoneNumber);

    //Optional<Worker> findByCinAndArchiveFalse(String cin);
    List<Worker> findAllByRecommendTrueAndIsWorkerTrue();

    Optional<Worker> findByPhoneNumberAndArchiveFalse(String phoneNumber1);


    @Query("SELECT w FROM Worker w WHERE " +
            "(:fullName IS NULL OR :fullName = '' OR w.fullName LIKE %:fullName%) " +
            "AND w.archive = false AND w.isWorker = true")
    Page<Worker> findAllByFullNameAndIsWorkerTrueWithPaginator(
            @Param("fullName") String fullName,
            Pageable pageable);
}
